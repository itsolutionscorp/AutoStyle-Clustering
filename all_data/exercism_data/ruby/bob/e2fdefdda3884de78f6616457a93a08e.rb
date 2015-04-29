class Bob

  def initialize
  	@question = "Sure."
  	@yell = "Whoa, chill out!"
  	@nothing = 'Fine. Be that way!'
  	@everything_else = "Whatever."
  end

  def hey(remark)
  	if remark.split(" ").empty? 
  		@nothing
  	elsif remark == remark.upcase && yelling?(remark)
  		@yell
  	elsif remark[-1].include?('?') 
  		@question
  	else
  		@everything_else
  	end
  end

  def yelling?(remark)
    a = remark.split(" ")
    a.collect {|x| return true if x.to_i == 0}
    false
  end
  
end
