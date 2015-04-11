class School
  attr_reader :db

  def initialize
    @db = {}
  end

  # Add a student to a class
  # If that class does not exist, create a new array with the name of the
  # student passed in for that class
  def add(name, class_number)
    return @db[class_number].push(name) unless @db[class_number].nil?

    @db[class_number] = [name]
  end

  # Return the array of the names of a student in the specified class
  # If there is no one in that class, return an empty array
  def grade(class_number)
    @db.fetch(class_number, [])
  end

  # Sort the classes by number and then sort the students in each class
  # alphabetically
  # Return the sorted hash
  def sort
    # sort the @db hash and the values of the students
    sorted_db = @db.sort.each do |class_number, students|
      students.sort!
    end

    # convert the sorted_db array back to a hash
    Hash[sorted_db]
  end
end
