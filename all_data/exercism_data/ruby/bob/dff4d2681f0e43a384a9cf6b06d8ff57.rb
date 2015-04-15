require_relative 'responder'

class Bob
  include Responder

  def hey(phrase)
    respond_to(phrase)
  end
end
