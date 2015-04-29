class School

  def initialize
   @db = Hash.new { |hash, key| hash[key] = [] }
  end

  def db
    @db.to_hash
  end

  def add (student, grade)
    @db[grade] << student
  end

  def grade (class_grade)
    @db[class_grade].sort!
  end

  def sort
    @db.key_sort.each_with_object({}) do |class_grade, sorted_hash|
      sorted_hash[class_grade] = grade(class_grade).sort
    end
  end

  def key_sort
    @db.keys.sort
  end

  def to_hash
    @db
  end


end
