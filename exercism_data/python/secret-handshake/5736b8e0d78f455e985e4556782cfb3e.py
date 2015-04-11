def handshake(arg):

    action_dict = {
        0: "wink",
        1: "double blink",
        2: "close your eyes",
        3: "jump"
    }

    if isinstance(arg, str):
        try:
            return handshake(int(arg, 2))

        except ValueError:
            # Catch when the string isn't a valid binary string
            # and return an empty list
            return []

    else:
        # We make the assumption here that we're dealing with an int.
        # Stop and return an empty list if we have a negative number
        if arg < 0:
            return []

        # Use a bitmask to construct the list of actions
        action_list = [action_dict[i] for i in range(4) if arg & (1 << i)]

        # Use a bitmask to see whether to reverse the actions
        if arg & (1 << 4):
            action_list.reverse()

        return action_list


def code(action_list):
    action_dict = {
        "wink": 1,
        "double blink": 1 << 1,
        "close your eyes": 1 << 2,
        "jump": 1 << 3
    }

    # If we've got any invalid actions, just return 0
    if any(action not in action_dict.keys() for action in action_list):
        return bin(0)[2:]

    # Add up the values of all of the actions
    action_sum = sum(action_dict.get(action) for action in action_list)

    # Check the action list against one sorted by the associated values
    # in the action_dict. If they're different, it's because it's been
    # reversed.
    if action_list != sorted(action_list, key=lambda x: action_dict.get(x)):
        # If they're reversed, add a 1 in the proper place.
        action_sum += 1 << 4

    # Return the binary representation, minus the '0b' at the front
    return bin(action_sum)[2:]
