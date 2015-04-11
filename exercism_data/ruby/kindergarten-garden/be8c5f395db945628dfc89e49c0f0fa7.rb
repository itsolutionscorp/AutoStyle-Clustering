class Garden
  Students = [:alice, :bob, :charlie, :david, :eve, :fred, :ginny, :harriet, :ileana, :joseph, :kincaid, :larry]

  attr_reader :row1, :row2, :students

  def initialize(sequence, students = Students)
    @row1, @row2 = sequence.split("\n").map(&:chars)
    @students = students.map(&:downcase).map(&:to_sym).sort
  end

  def method_missing(id, *args)
    index = students.find_index(id)
    if index
      characters_from(index * 2)
    else
      super
    end
  end

  def characters_from(start)
    [row1[start], row1[start + 1], row2[start], row2[start + 1]].map { |char| letter_to_plant(char) }
  end

  def letter_to_plant(letter)
    {
      'R' => :radishes,
      'C' => :clover,
      'G' => :grass,
      'V' => :violets
    }.fetch(letter)
  end
end
