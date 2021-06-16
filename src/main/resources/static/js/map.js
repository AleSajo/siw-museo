// Initialize and add the map
function initMap() {
    // The location of Museo
    const museo = { lat: 41.6710388592844, lng: 12.502150804878713 };
    // The map, centered at Museo
    const map = new google.maps.Map(document.getElementById("map"), {
      zoom: 12,
      center: museo,
    });
    // The marker, positioned at Museo
    const marker = new google.maps.Marker({
      position: museo,
      map: map,
    });
  }