class School
  def initialize
    @db = Hash.new { |h, k| 
      h[k] = Array.new
    }
  end

  def db
    @db.keys.sort.each_with_object({}) do |grade, new_db|
      new_db[grade] = @db[grade].dup
    end
  end

  def add(student, grade)
    @db[grade] << student
  end

  def grade(number)
    @db[number]
  end

  def sort
    db.tap { |clone_db|
      clone_db.keys.each do |grade|
        clone_db[grade].sort!
      end
    }
  end
end
