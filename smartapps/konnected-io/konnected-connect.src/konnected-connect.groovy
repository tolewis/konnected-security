/**
 *  Konnected
 *
 *  Copyright 2017 konnected.io
 *
 *  Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except
 *  in compliance with the License. You may obtain a copy of the License at:
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing, software distributed under the License is distributed
 *  on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the License
 *  for the specific language governing permissions and limitations under the License.
 *
 */
definition(
  name:        "Konnected (Connect)",
  namespace:   "konnected-io",
  author:      "konnected.io",
  description: "Konnected devices bridge wired things with SmartThings",
  category:    "Safety & Security",
  iconUrl:     "https://raw.githubusercontent.com/konnected-io/docs/master/assets/images/KonnectedSecurity.png",
  iconX2Url:   "https://raw.githubusercontent.com/konnected-io/docs/master/assets/images/KonnectedSecurity@2x.png",
  iconX3Url:   "https://raw.githubusercontent.com/konnected-io/docs/master/assets/images/KonnectedSecurity@3x.png",
  singleInstance: true
)

preferences {
  page(name: "mainPage", title: "Konnected Devices", install: true, uninstall: true) {
    section {
      app(name: "childApps", appName: "Konnected Service Manager", namespace: "konnected-io", title: "Add a Konnected device", multiple: true)
    }
  }
}


def installed() {
  log.info "installed(): Installing Konnected SmartApp"
}

def updated() {
  log.info "updated(): Updating Konnected SmartApp"
}

def uninstalled() {
  log.info "uninstall(): Uninstalling Konnected SmartApp"
}

void registerKnownDevice(mac) {
  if (state.knownDevices == null) {
    state.knownDevices = [].toSet()
  }
  state.knownDevices.add(mac)
}

void removeKnownDevice(mac) {
   state.knownDevices?.remove(mac)
}

Boolean isNewDevice(mac) {
  log.debug "Known devices: $state.knownDevices"
  return !state.knownDevices?.contains(mac)
}