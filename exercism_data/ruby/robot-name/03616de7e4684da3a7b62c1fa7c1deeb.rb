class Robot
  LETTERS = ('A'..'Z').to_a + ('a'..'z').to_a

  def self.next_name
    @id = @id ? @id + 1 : 0
    namegen(@id)
  end

  def self.namegen(id)
    aa, nnn = id.divmod(1000)
    a1, a2 = aa.divmod(LETTERS.length)
    numbers = "%c%c%03d" % [LETTERS[a1], LETTERS[a2], nnn]
  end

  def name
    @name ||= self.class.next_name
  end

  def reset
    @name = nil
  end
end
