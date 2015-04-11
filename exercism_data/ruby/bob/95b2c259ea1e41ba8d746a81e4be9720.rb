#!/usr/bin/env ruby
# encoding: utf-8
# Esta clase recibe una sentencia y evalua si esta vacia,
# con mayusculas, pregunta o cualquier otro
class Bob
  def hey(sentencia)
    # Si sentencia esta vacia
    if sentencia.strip.empty?
      return 'Fine. Be that way!'
      # Si se compone de alfabeto y son mayusculas...
    # elsif sentencia =~ /[A-Z]/ && sentencia.upcase == sentencia
    elsif silent?(sentencia)
      return 'Woah, chill out!'
      # Si se compone de alfabeto y son mayusculas...
    elsif sentencia.end_with?('?')
      return 'Sure.'
    else
      # Si se compone de alfabeto y son mayusculas...
      return 'Whatever.'
    end
  end

  def silent?(sentencia)
    sentencia =~ /[A-Z]/ && sentencia.upcase == sentencia
  end
end
