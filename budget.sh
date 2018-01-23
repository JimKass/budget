#!/usr/bin/env bash

usage () {
    cat << EOF
${txtbld}SYNOPSIS${txtrst}
    $0 is used to keep track of payslip, payments and estimate the amount of pay will be received.

${txtbld}USAGE${txtrst}
    ./budget <OPTION> <PARAMS>

${txtbld}OPTIONS AND PARAMS${txtrst}
    payslip

    payment

    overview

    estimate


EOF
}

parse () {
    case $1 in
        -help|-h|"")
            usage ;;
        -test|-t)
            lein test ;;
        payslip)
            lein exec -p ./src/budget/core.clj ;;
        estimate)
            lein exec -p ./src/budget/core.clj ;;
        overview|-o)
            lein exec -p ./src/budget/core.clj ;;
        payment)
            lein exec -p ./src/budget/core.clj ;;
    esac
}

parse $@
