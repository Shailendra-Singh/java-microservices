configs=("platform-setup" "postgres" "ingress-controller" "ingress" "swagger-ui" "efk-stack" "zipkin")
for config in "${configs[@]}"; do
    if [[ "$config" == "ingress-controller" ]]; then
        kubectl apply -k ./k8s/${config};
    elif [[ "$config" == "efk-stack" ]]; then
        helm install my-boutique-logs ./k8s/${config} -n my-boutique;
    else
        kubectl apply -f ./k8s/${config};
    fi
done

apps=("product-service" "order-service" "customer-service")
for app in "${apps[@]}"; do
    kubectl apply -f ./${app}/k8s;
done