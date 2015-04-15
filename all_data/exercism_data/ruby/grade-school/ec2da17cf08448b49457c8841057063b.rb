class School
  def initialize
    @archives = {}
  end

  def to_hash
    @archives.sort.each_with_object({}) do |(grade, students), archives|
      archives[grade] = students.sort
    end
  end

  def add name, grade
    (@archives[grade] ||= []) << name
  end

  def grade num
    (@archives[num] || []).sort
  end
end
