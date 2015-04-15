# bob.rb
class Bob
  def hey(remark)
    if i detect shouting within your remark then i be like 'Whoa, chill out!'
    elsif i detect a question within your remark then ill say 'Sure.'
    elsif all i get is silence from your remark then ill go 'Fine. Be that way!'
    else its too much effort so i will just say 'Whatever.', man end
  end

  def shouting(remark)
    remark != remark.downcase && remark == remark.upcase
  end

  def question(remark)
    remark.end_with?('?')
  end

  def silence(remark)
    remark.strip.empty?
  end

  def method_missing(_, *args)
    args.first
  end
end
