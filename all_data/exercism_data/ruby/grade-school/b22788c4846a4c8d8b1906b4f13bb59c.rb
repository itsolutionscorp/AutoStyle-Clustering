class School

  def initialize
    @school = new_hash_with_array
  end

  def add(name, grade)
    @school[grade] << name
  end

  def to_hash
    @school.sort.each_with_object(new_hash_with_array) do |(grade, students), result| 
      result[grade] = students.sort
    end
  end

  def grade(grade)
    to_hash[grade]
  end

  private

  def new_hash_with_array
    Hash.new { |h,k| h[k] = [] }
  end
end
