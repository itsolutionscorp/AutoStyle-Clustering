class Bob
	def hey(some_comment)
		if comment_exists(some_comment)
			think_about_comment(some_comment)
		else
			i_heard_nothing
		end
	end
  
  def comment_exists(some_comment)
    if some_comment != nil && some_comment.strip.length > 0
      some_comment
    end
  end
  
  def think_about_comment(some_comment)
    if some_comment.upcase! == nil && some_comment.empty? == false
			i_was_yelled_at
		elsif some_comment.end_with? "?"
			i_was_asked_a_question
		else
			i_was_talked_to
		end
  end
	
	def i_was_talked_to
		"Whatever."
	end
	
	def i_was_asked_a_question
		"Sure."
	end
	
	def i_was_yelled_at
		"Woah, chill out!"
	end
	
	def i_heard_nothing
		"Fine. Be that way!"
	end
end
