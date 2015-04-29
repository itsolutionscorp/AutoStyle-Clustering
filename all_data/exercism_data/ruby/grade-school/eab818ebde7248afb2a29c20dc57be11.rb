class School

  def initialize
    @database = {}
  end

  def db
    @database
  end

  def add(pupil, grade)
    if(pupils_exist_in_grade?(grade))
      pup_arr = @database.values_at(grade).flatten
      pup_arr << pupil
      @database[grade] = pup_arr
    else
      @database[grade] = [pupil]
    end
  end

  def grade(num)
    if pupils_exist_in_grade?(num)
      @database.values_at(num).flatten
    else
      []
    end
  end

  def pupils_exist_in_grade?(grade)
    @database.values_at(grade) != [nil]
  end

  def sort
    database_sorted = @database.map { |grade, pupil| [grade, pupil.sort] }.sort
    @database = Hash[database_sorted]
  end
end
