class AppBase {
    static DOMAIN = location.origin;

    static API_CUSTOMER = this.DOMAIN  + "/api/customers";
    static API_DEPOSIT = this.DOMAIN + "/api/deposits";
    static API_WITHDRAW = this.DOMAIN + "/api/withdraws";
    static API_TRANSFER = this.DOMAIN + "/api/transfers";

    static BASE_URL_PRODUCT = this.DOMAIN + "/api/products";

    static showSuccessAlert(t) {
        Swal.fire({
            icon: 'success',
            title: t,
            position: 'top-end',
            showConfirmButton: false,
            timer: 1500
        })
    }

    static showErrorAlert(t) {
        Swal.fire({
            icon: 'error',
            title: 'Warning',
            text: t,
        })
    }
}