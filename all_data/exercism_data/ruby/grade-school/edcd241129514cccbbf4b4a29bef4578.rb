class School

  attr_reader :grades_arr,:school_data
  
  def initialize()
    @grades_arr ||= Array.new
    @school_data ||= Hash.new
    
  end
  
  def add(name,grade)
      @grades_arr.push([name,grade])
      
  end
  
  def to_hash
    # p @grades_arr
      @grades_arr.each{|s|
        if @school_data.has_key?(s[-1])
            @school_data[s[-1]] << (s[0])
        else
            @school_data[s[-1]] = [s[0]]
        end
      }
    #p @school_data.each_value{|v| v.sort!}.sort.to_h
    @school_data.each_value{|v| v.sort!}.sort.to_h
  end
  
  def grade(grade_num)
     to_hash
     if @school_data.has_key?(grade_num) 
       @school_data[grade_num].sort
     else
        []
     end
    
  end

end
