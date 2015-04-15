class Year
  def self.leap?(input)
    fourthury?(input) && (century?(input) || fourcentury?(input))
  end

  protected

  def self.century?(input)
    input % 100 != 0
  end

  def self.fourthury?(input)
    input % 4 == 0
  end

  def self.fourcentury?(input)
    input % 400 == 0
  end
end
