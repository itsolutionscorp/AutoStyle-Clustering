class School
  
  attr_reader :name, :db
  def initialize(name)
    @name = name
    @db = Hash.new { |hash, key| hash[key] = [] }
  end

  def add(person, grade)
    @db[grade] << person
  end

  def grade(num)
    @db[num]
  end

  def sort
    Hash[with_sorted_students.sort]
  end

  private

    def with_sorted_students
      @db.each do |k,v|
        @db[k] = v.sort
      end
    end

end
