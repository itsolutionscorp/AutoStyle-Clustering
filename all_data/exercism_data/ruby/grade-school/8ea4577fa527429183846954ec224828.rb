class School
  def db
    @students
  end

  def add(name, grade)
    if @students[grade].nil?
      @students[grade] = [name]
    else
      @students[grade].push(name)
    end
  end

  def grade(grade)
    if @students.include?(grade)
      @students[grade]
    else
      []
    end
  end

  def sort
    @students.values.each {|array| array.sort!}
    Hash[@students.sort]
  end

  private
    def initialize
      @students = Hash.new
    end
end
