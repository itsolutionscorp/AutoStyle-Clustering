class School

  def initialize
    @data = Hash.new([])
  end

  def to_hash
    @data = Hash[@data.sort]
    @data.each { |k, v| @data[k] = v.sort }
  end

  def has_grade? grade
    @data.keys.include? grade
  end

  def add(student, grade)
    @data[grade] = [] unless has_grade? grade
    @data[grade] << student
  end

  def grade num
    @data[num].sort
  end
end
