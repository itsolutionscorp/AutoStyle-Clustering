class School 
  def db
    @db ||= {}
	end

  def add(name,grade_num)
    @grade_arr = grade(grade_num)
    @grade_arr << name
    db[grade_num] = @grade_arr
  end

  def grade(grade_num)
    @grade_arr = db[grade_num] || @grade_arr = []    
  end

  def sort
    sorted_ary = db.sort
    p sorted_ary
    db = Hash[sorted_ary].each_value {|value| value.sort! }
  end

end
