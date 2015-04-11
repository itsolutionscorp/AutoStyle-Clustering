class Raindrops
  def self.convert(integer)
    converted = ''

    converted << "Pling" if divisible_by_three?(integer)
    converted << "Plang" if divisible_by_five?(integer)
    converted << "Plong" if divisible_by_seven?(integer)

    if !converted.empty?
      converted
    else
      integer.to_s
    end
  end

  private

  def self.divisible_by_three?(integer)
    integer % 3 == 0
  end

  def self.divisible_by_five?(integer)
    integer % 5 == 0
  end

  def self.divisible_by_seven?(integer)
    integer % 7 == 0
  end
end
