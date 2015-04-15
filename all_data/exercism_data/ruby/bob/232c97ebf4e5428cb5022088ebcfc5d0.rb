class Bob
	def hey(some_comment)
		if comment_exists(some_comment)
			think_about_comment(some_comment)
		else
			"Fine. Be that way!"
		end
	end
  
  def comment_exists(some_comment)
    if some_comment != nil && some_comment.strip.length > 0
      some_comment
    end
  end
  
  def think_about_comment(some_comment)
    if some_comment.upcase! == nil && some_comment.empty? == false
			"Woah, chill out!"
		elsif some_comment.end_with? "?"
			"Sure."
		else
			"Whatever."
		end
  end
end
