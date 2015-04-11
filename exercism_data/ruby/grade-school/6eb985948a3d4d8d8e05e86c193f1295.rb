class School
  def initialize
    @school = Hash.new { |h,k| h[k] = [] }
  end

  def to_hash
    @school.each { |grade, students| students.sort! }.sort_by { |k, v| k }.to_h
  end

  def add(name, grade)
    @school[grade] << name
  end

  def grade(year)
    @school[year].sort
  end
end
