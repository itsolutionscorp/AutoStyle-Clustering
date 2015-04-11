import re

class Bob:
  def hey(self,statement):
    return self.appropriate_response_for(statement).text

  def appropriate_response_for(self,statement):
    valid_responses = filter(lambda response: response.does_match(statement),self.possible_responses())
    return valid_responses[0]

  def possible_responses(self):
    return [

      self.response(
        when_the_message_matches = lambda msg: msg.strip() == "",
        i_would_say = "Fine. Be that way!",
        because = "Unbearable Silence Breeds Resentment"),

      self.response(
        when_the_message_matches = lambda msg: re.search("[A-Z]+",msg) and msg == msg.upper(),
        i_would_say = "Woah, chill out!",
        because = "Yelling Only Pushes Me Away"),

      self.response(
        when_the_message_matches = lambda msg: msg.endswith("?"),
        i_would_say = "Sure.",
        because = "I Give Apathetic Answers to Stupid Questions"),

      self.default_response()
    ]

  def default_response(self):
    return self.response(
      when_the_message_matches = lambda msg: True,
      i_would_say = "Whatever.",
      because = "I Heard You But I Did Not Listen")


  def response(self,when_the_message_matches,i_would_say,because):
    return CallAndResponse(because,when_the_message_matches,i_would_say)

class CallAndResponse:
  def __init__(self,name,when_the_message_matches,i_would_say):
    self.name = name
    self._match = when_the_message_matches
    self.text = i_would_say

  def does_match(self,statement):
    return self._match(statement)
