class School
  def initialize
    @records = {}
  end

  def db
    @records
  end

  def add(name, grade)
    if @records.empty?
      @records[grade] = [name]
    elsif @records.has_key?(grade)
      @records[grade] << name
    else
      @records[grade] = [name]
    end
  end

  def grade(grade)
    students = @records[grade]
    students.nil? ? [] : students
  end

  def sort
    sorted = {}
    keys = @records.keys.sort
    keys.each do |key|
      sorted[key] = @records[key].sort
    end
    sorted
  end
end
