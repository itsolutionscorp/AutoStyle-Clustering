#!/usr/bin/env ruby
trap("INT") { exit }

class Bob

  def hey msg
    if silent_treatment? msg
      'Fine. Be that way.'
    elsif too_loud? msg
      'Woah, chill out!'
    elsif inquiry? msg
      'Sure.'
    else
      'Whatever.'
    end
  end

  private
  def silent_treatment? msg
    msg.empty?
  end

  def too_loud? msg
    msg == msg.upcase
  end

  def inquiry? msg
    msg.end_with? '?'
  end
end

class App
  # I probably didn't need to include the command line clinet.
  def self.start bob
    app = self.new bob
    app.main
    app.exit
  end

  def initialize bob
    @bob = bob
  end

  def main
    begin
      welcome
      main_loop
    ensure
      bob_gets_the_last_word
    end
  end

  def welcome
    puts "RTFM"
  end

  def main_loop
    while true
      message = gets
      puts @bob.hey message.strip
    end
  end

  def bob_gets_the_last_word
    puts "\n" + @bob.hey('')
  end
end

App.start Bob.new
