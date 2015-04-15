class Bob
  def hey(dialog)
    case
      when dialog.end_with?("?") && dialog.scan(/[A-Z]{3,}/).empty?
        'Sure.'

      when !dialog.scan(/[A-Z]{2,}/).empty? && dialog.scan(/[a-z]{2,}/).empty?
        'Woah, chill out!'

      when dialog.empty? || dialog.scan(/\S/).empty?
        'Fine. Be that way!'
        
      else
        'Whatever.'
    end
  end
end
