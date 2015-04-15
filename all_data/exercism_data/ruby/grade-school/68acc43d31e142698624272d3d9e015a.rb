class School

  def initialize
    @db = Hash.new {|hash, key| hash[key] = [] }
  end

  def db
    copied_entries = @db
      .map {|(grade, students)| [grade, students.dup]}

    Hash[ copied_entries ]
  end

  def add student, grade
    @db[grade] << student
  end

  def grade grade
    @db[grade].dup
  end

  def sort
    sorted_entries = @db
      .sort_by {|(grade)| grade }
      .map {|(grade, students)| [grade, students.sort] }

    Hash[ sorted_entries ]
  end

end
