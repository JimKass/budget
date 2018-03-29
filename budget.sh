#!/usr/bin/env bash

usage () {
    cat << EOF
${txtbld}SYNOPSIS${txtrst}
    $0 is used to keep track of payslip, payments and estimate the amount of pay will be received.

${txtbld}USAGE${txtrst}
    ./budget <OPTIONS> COMMAND <PARAMS>

${txtbld}COMMANDS${txtrst}
    help            prints usage of specified command. Prints this usage if none are specified
    help-all        prints usage of all command.
    test            runs a test on the program
    payslips        prints payslips
    payments        view payments
    add-payment     add a new payment
EOF
}

payslip-usage () {
    cat << EOF
${txtbld}SYNOPIS${txtrst}
    Displays payslips

${txtbld}USAGE${txtrst}
    ./budget payslips

${txtbld}PARAMETERS${txtrst}
    --count             defines the amount of payslips that will be printed
    -- average | -a     tells the program to print the average income from the printed payslips.
EOF
}

payments-usage () {
    cat << EOF

EOF
}

add-payment-test () {
    cat << EOF

EOF
}

test-usage () {
    cat << EOF

EOF
}



parse () {
    case $1 in
        --help|-h|"")
            usage ;;
        test)
            lein test ;;
        payslip)
            lein exec -p ./src/budget/core.clj ;;
        payment)
            lein exec -p ./src/budget/core.clj ;;
        add-payment)
            lein exec -p ./src/budget/core.clj ;;
        help-all)
            usage
    esac
}

parse $@
