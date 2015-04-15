class School
  def hash_of_arrays
    Hash.new { |hash, key| hash[key] = [] }
  end

  def initialize
    @db = hash_of_arrays
  end

  def to_hash
    @db.sort.each_with_object(hash_of_arrays) do |(grade, students), db_copy|
      students.each do |student|
        db_copy[grade] << student.dup
      end
      db_copy[grade].sort!
    end
  end

  def add(name, grade)
    @db[grade] << name
  end

  def grade(n)
    to_hash[n]
  end
end
