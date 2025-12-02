static void newReservation() {
    System.out.print("Input Guest Name: ");
    String guest = kbd.nextLine();

    int type = 0;

    while (type < 1 || type > 3) {
        System.out.print("Input Room Type: (1. Standard, 2. Deluxe, 3. Suite): ");
        type = Integer.parseInt(kbd.nextLine());

        if (type < 1 || type > 3) {
            System.out.println("Invalid. Please enter 1, 2, or 3 only.");
        }
    }


    String[][] selectedArray;
    String roomPrefix = "";
    int price = 0;

    switch (type) {
        case 1 -> {
            selectedArray = standard;
            roomPrefix = "S";
            price = 2500;
        }
        case 2 -> {
            selectedArray = deluxe;
            roomPrefix = "D";
            price = 4000;
        }
        case 3 -> {
            selectedArray = suite;
            roomPrefix = "T";
            price = 8000;
        }
        default -> {
            System.out.println("Invalid Room Type.");
            return;
        }
    }

    System.out.println();
    printTable(selectedArray, roomPrefix.charAt(0));


    int nights = 0;

    while (nights < 1 || nights > 10) {
        System.out.print("\nInput number of days (1–10): ");
        nights = Integer.parseInt(kbd.nextLine());

        if (nights < 1 || nights > 10) {
            System.out.println("Invalid. Please enter a number between 1 and 10.");
        }
    }

    for (int i = 1; i <= nights; i++) {
        System.out.println("Date " + i + ": Day " + (i + 2));
    }

    System.out.println("\nProcessing Reservation...");

    int assignedRoomIndex = -1;

    for (int i = 0; i < selectedArray.length; i++) {
        boolean free = true;

        for (int d = 0; d < nights; d++) {
            if (!selectedArray[i][d].equals("Available")) {
                free = false;
                break;
            }
        }

        if (free) {
            assignedRoomIndex = i;
            for (int d = 0; d < nights; d++) {
                selectedArray[i][d] = "Booked";
            }
            break;
        }
    }

    if (assignedRoomIndex == -1) {
        System.out.println("No available rooms for this duration.");
        return;
    }

    int roomNumber = 101 + assignedRoomIndex;
    String fullRoomName = roomPrefix + roomNumber;

    int fee = price * nights;

    System.out.println("Found: " + fullRoomName + ".");
    System.out.println("Reservation Fee (Room Rate Only): ₱" + price + " / night * " + nights + " nights = ₱" + fee);

    System.out.println("\n--- Reservation Summary ---");
    System.out.println("Guest Name: " + guest);

    String roomTypeName = "";
    if (type == 1) {
        roomTypeName = "Standard";
    } else if (type == 2) {
        roomTypeName = "Deluxe";
    } else {
        roomTypeName = "Suite";
    }
    System.out.println("Room Type: " + roomTypeName);

    System.out.println("Room Number Assigned: " + fullRoomName);
    System.out.println("Nights Booked: " + nights);
    System.out.println("Total Reservation Fee Due Now: ₱" + fee);
    System.out.println("Update Status: Room " + fullRoomName + " is now set to 'Booked' by " + guest + ".");
    System.out.println();
}