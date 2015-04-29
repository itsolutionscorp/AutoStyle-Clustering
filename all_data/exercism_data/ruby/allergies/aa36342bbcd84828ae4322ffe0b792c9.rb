class Allergies
  SCORES = { 'eggs'         => 1,
             'peanuts'      => 2,
             'shellfish'    => 4,
             'strawberries' => 8,
             'tomatoes'     => 16,
             'chocolate'    => 32,
             'pollen'       => 64,
             'cats'         => 128 }

  attr_reader :score, :list

  def initialize n
    @score = prep n
    @list = determine_allergies score
  end

  def allergic_to? allergen
    list.include? allergen
  end

  private

  def prep n
    n -= 128 until n <= 255
    n
  end

  def determine_allergies n
    scores = SCORES.values.sort.select { |s| s <= n }
    1.upto(scores.size).with_object([]) do |i, a|
      scores.combination i do |combi|
        combi.each { |e| a << SCORES.invert[e] } if combi.reduce(:+) == n
      end
    end
  end
end
