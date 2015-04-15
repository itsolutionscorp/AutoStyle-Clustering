# Bob is a lackadaisical teenager. In conversation, his responses are very limited.

# Bob answers 'Sure.' if you ask him a question.

# He answers 'Whoa, chill out!' if you yell at him.

# He says 'Fine. Be that way!' if you address him without actually saying
# anything.

# He answers 'Whatever.' to anything else.

class Bob
  
  def hey(remark)
    remark.gsub!(/[\t]/, "")

    case 
    when remark.chars.all? {|l| l == " "}
      'Fine. Be that way!'
    when remark.chars.none? {|l| /[a-z]/i.match(l)}
      if remark[-1] == "?"
        "Sure."
      else
        "Whatever."
      end
    when remark == remark.upcase
      'Whoa, chill out!'
    when remark[-1] ==  "?" 
      'Sure.'
    else
      "Whatever."
    end
  end

 
end
