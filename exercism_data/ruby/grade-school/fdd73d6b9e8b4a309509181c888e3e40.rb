class School
  def initialize
    @hash = Hash.new { [] }
  end

  def add(name, grade)
    @hash[grade] <<= name
  end

  def to_hash
    @hash.sort.each_with_object({}) do |(grade, names), sorted_hash|
      sorted_hash[grade] = names.sort
    end
  end

  def grade(grade)
    @hash.fetch(grade) { [] }.sort
  end
end
