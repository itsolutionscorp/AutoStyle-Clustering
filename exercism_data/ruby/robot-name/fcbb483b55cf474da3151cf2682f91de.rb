class Robot

  LastName = [ 'A'.ord, 'A'.ord, -1]

  def self.name
    l1, l2, num = LastName
    num += 1
    if num == 1000
      num = 0
      l2 += 1
    end

    if l2 == 'Z'.ord+1
      l2 = 'A'.ord
      l1 += 1
    end
    raise 'No more Names' if l1 == 'Z'.ord+1

    LastName[0] = l1
    LastName[1] = l2
    LastName[2] = num
    l1.chr + l2.chr + '%03d' % num
  end

  def initialize
    @name = nil
  end

  def name
    # binding.pry
    @name ||= Robot.name
    @name
  end

  def reset
    @name = nil
  end

end
