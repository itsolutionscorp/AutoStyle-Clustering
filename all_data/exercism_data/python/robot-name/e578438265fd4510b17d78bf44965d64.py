class Robot(object):

  last_name = ['A','A', "000"]

  def __init__(self):
    self.new_name()

  def reset(self):
    self.new_name()

  def new_name(self):
    self.name =  "".join(Robot.last_name)
    if int(Robot.last_name[2]) < 999:
      Robot.last_name[2] = str(int(Robot.last_name[2]) + 1).zfill(3)
    elif ord(Robot.last_name[2]) < ord('Z'):
      Robot.last_name[2] = "000"
      Robot.last_name[1] = chr(ord(Robot.last_name[1]) + 1)
    else:
      Robot.last_name[2] = "000"
      Robot.last_name[1] = 'A'
      Robot.last_name[0] = chr(ord(Robot.last_name[0]) + 1)
