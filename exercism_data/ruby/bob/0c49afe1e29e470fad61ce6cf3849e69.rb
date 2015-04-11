# encoding: UTF-8
# Clase Bob
class Bob
  def hey(frase)
    case
    when frase.strip.empty?
      'Fine. Be that way!'
    when frase.end_with?('?')
      if frase == frase.upcase
        if !(frase.count('A-Z') > 0)
          'Sure.'
        else
          'Woah, chill out!'
        end
      else
        'Sure.'
      end
    when frase == frase.upcase
      if frase.count('A-Z') > 0
        'Woah, chill out!'
      else
        'Whatever.'
      end
    else
      'Whatever.'
    end
  end
end
