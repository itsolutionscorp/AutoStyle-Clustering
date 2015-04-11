# Clase Bob
class Bob
    # Metodo hey a heredar
  def hey(cadena)
    case
    when /\S/.match(cadena).nil?
      return 'Fine. Be that way!'
    when cadena.index('?') == cadena.length - 1
      return 'Sure'
    when cadena.upcase == cadena
      return 'Woah, chill out!'
    else
      return 'Whatever.'
    end
  end
end
