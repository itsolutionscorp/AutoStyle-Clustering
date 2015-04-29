class School
  
  attr_reader :name, :db
  def initialize(name)
    @name = name
    @db = {}
  end

  def add(person, grade)
    people = Array @db[grade]
    people << person
    @db[grade] = people
  end

  def grade(num)
    @db[num].to_a
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
