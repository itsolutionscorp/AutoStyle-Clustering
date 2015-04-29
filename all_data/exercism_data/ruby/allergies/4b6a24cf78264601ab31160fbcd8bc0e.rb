class Allergies
  attr_reader :score

  LOOKUP = [["cats", 128], ["pollen", 64], ["chocolate", 32], ["tomatoes", 16],
            ["strawberries", 8], ["shellfish", 4], ["peanuts", 2], ["eggs", 1]]

  def initialize(score)
    @score = score
  end

  def allergic_to?(item)
    list.include?(item)
  end

  def list
    @list ||= make_list
  end

  private

  def make_list
    tally_score = score
    LOOKUP.each_with_object([]) do |(item, rating), list|
      if tally_score >= rating
        list.unshift(item)
        tally_score %= rating
      end
    end
  end

end
