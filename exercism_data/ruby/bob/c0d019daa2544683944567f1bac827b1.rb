class Bob
  attr_accessor :request, :response

  def hey(request)
    clear_previous_response!
    log_current_request! request

    handle_silence
    handle_shouting
    handle_questions
    respond_with_default
  end

private

  def clear_previous_response!
    @response = nil
  end

  def log_current_request!(request)
    @request = request
  end

  def handle_shouting
    return if response
    set_response('Woah, chill out!') if request.upcase == request
  end

  def handle_questions
    return if response
    set_response('Sure.') if request =~ /\?$/
  end

  def handle_silence
    return if response
    set_response 'Fine. Be that way.' if request.nil? || request.empty?
  end

  def set_response(content)
    @response = content
  end

  def respond_with_default
    @response || 'Whatever.'
  end
end
