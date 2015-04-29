#!/usr/bin/env ruby
# encoding: utf-8
# Esta clase recibe una sentencia y evalua si esta vacia,
# con mayusculas, pregunta o cualquier otro
class Bob
  def hey(sentencia)
    # Si sentencia esta vacia
    if empty?(sentencia)
      return 'Fine. Be that way!'
      # Si se compone de alfabeto y son mayusculas...
    elsif silent?(sentencia)
      return 'Woah, chill out!'
      # Si se compone de alfabeto y son mayusculas...
    elsif end_with?(sentencia)
      return 'Sure.'
    else
      # Si se compone de alfabeto y son mayusculas...
      return 'Whatever.'
    end
  end
  # Determinarr si son puras mayusculas y solo contiene letras.
  def silent?(sentencia)
    # sentencia =~ /[A-Z]/ && sentencia.upcase == sentencia
    /[A-Z]/.match(sentencia) && sentencia.upcase == sentencia
  end
  # Determinar if empty
  def empty?(sentencia)
    sentencia.strip.empty?
  end
  # Determinar si termina en ?
  def end_with?(sentencia)
    sentencia.end_with?('?')
  end
end
