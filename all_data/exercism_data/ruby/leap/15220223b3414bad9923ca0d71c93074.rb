class Year
  def self.leap?(n)
    n.modulo(4).zero? and (n.modulo(400).zero? or not n.modulo(100).zero?)
  end
end
