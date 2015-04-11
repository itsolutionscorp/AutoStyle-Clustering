def proverb(things, qualifier=""):
    lost_lines = ["For want of a {} the {} was lost.".format(wanted, lost)
                  for (wanted, lost) in zip(things, things[1:])]
    wanted_lines = ["And all for the want of a {}{}.".format(
                        qualifier + (' ' if qualifier else ''), things[0])]
    return '\n'.join(lost_lines + wanted_lines)
