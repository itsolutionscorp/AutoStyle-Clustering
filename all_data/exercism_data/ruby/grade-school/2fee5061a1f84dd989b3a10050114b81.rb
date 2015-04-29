class School

  def initialize
    @db = Hash.new([])
  end

  def add(student, grade)
    @db[grade] += Array(student)
  end

  def grade(level)
    @db[level].sort
  end

  def to_hash
    sort_all
    @db
  end

  private

  def sort_all
    @db = @db.sort.map.with_object({}) do |(grade, students), sorted|
      sorted[grade] = students.sort
    end
  end

end
