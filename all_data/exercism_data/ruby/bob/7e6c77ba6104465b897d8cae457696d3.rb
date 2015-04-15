module StringHelpers
  refine String do
    def blank?
      self.to_s.strip == ""
    end

    def question?
      self.end_with? "?"
    end

    def all_caps?
      self == self.upcase
    end
  end
end

class Responses

  def initialize(person_type)
    case person_type
    when :teenager
      @response = {
        blank: "Fine. Be that way!",
        all_caps: "Woah, chill out!",
        question: "Sure.",
        default: "Whatever."
      }
    else
      @response = {}
    end
  end

  def method_missing(method_name, *args, &block)
    name = match_name(method_name)
    if valid_keys.include? name
      Responses.instance_eval do
        define_method method_name do
          send(:response, name)
        end
      end
      send(:response, name)
    else
      super
    end
  end

  def respond_to_missing?(method_name, include_private = false)
    name = match_name(method_name)
    valid_keys.include? name || super
  end

  private
  def match_name(name)
    match = name.to_s.match(/(.*)_response$/)
    match[1] || ""
  end

  def valid_keys
    @response.keys.to_s
  end

  def response(type)
    @response.fetch(type.to_sym)
  end
end


using StringHelpers
class Bob
  def hey(statement)
    statement = statement.to_s # sanity check to make sure we have one even if nil
    teen = Responses.new :teenager

    return teen.blank_response    if statement.blank?
    return teen.all_caps_response if statement.all_caps?
    return teen.question_response if statement.question?
    teen.default_response
  end
end
