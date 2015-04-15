# bob.rb
class Bob
  def hey(remark)
    case remark
    when /^(?!.*\p{Ll}).*\p{Lu}/
      'Whoa, chill out!'
    when /\?\z/
      'Sure.'
    when /\A\s*\z/
      'Fine. Be that way!'
    else
      'Whatever.'
    end
  end
end
