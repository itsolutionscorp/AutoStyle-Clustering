class School
  def initialize
  end

  def db
    @db ||= Hash.new { |hash, key| hash[key] = [] }
  end

  def add(student, grade)
    db[grade] << student
  end

  def grade(grade)
    db[grade]
  end

  def sort
    sorted_array = db.sort.each do |array|
      array[1].sort!
    end
    Hash[sorted_array]
  end
end
