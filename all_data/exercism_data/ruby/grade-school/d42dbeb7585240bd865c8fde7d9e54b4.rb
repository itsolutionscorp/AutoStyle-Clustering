class School
  attr_reader :db

  def initialize
    @db = {}
  end

  def add(name, class_number)
    return @db[class_number].push(name) unless @db[class_number].nil?

    @db[class_number] = [name]
  end

  def grade(class_number)
    @db.fetch(class_number, [])
  end

  def sort
    sorted_db = @db.sort.each do |class_number, students|
      students.sort!
    end

    Hash[sorted_db]
  end
end
