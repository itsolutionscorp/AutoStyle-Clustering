class School
  def initialize
    @db = Hash.new {|h,grade| h[grade] = [] }
  end

  def add(name, grade)
    @db[grade] << name
  end

  def grade(grade)
    @db[grade].dup
  end

  def db
    @db.dup
  end

  def sort
    @db.sort.inject({}) {|sorted, (grade,students)|
      sorted[grade] = students.sort
      sorted
    }
  end
end
