class Elevator:
    def __init__(self):
        self.currentFloor = 0
        self.requests = []

    def handleRequest(self, floor):
        self.requests.add(floor)
