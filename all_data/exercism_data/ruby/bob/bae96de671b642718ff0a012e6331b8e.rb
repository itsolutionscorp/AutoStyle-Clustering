require 'active_support'
require 'active_support/core_ext'

module StringUtils
  NOT_DIGITS = /[^[:digit:]]/
  ONLY_DIGITS = /[[:digit:]]/
  PUNCTUATION = /[[:punct:]]/

  def ends_with_?(str)
    str.ends_with?('?')
  end

  def ends_with_!(str)
    str.ends_with?('!')
  end

  def original_eqls_uppercase?(str)
    str == str.upcase
  end
end

class Bob
  include StringUtils

  def hey(comment)
    if sass? comment
      return 'Fine. Be that way!'
    elsif acquiescence? comment
      return 'Sure.'
    elsif plea_to_chill? comment
      return 'Whoa, chill out!'
    end
    return 'Whatever.'
  end

  private

  def plea_to_chill?(comment)
    if ends_with_! comment
      if original_eqls_uppercase? comment
        return true
      end
    elsif original_eqls_uppercase? comment
      unless comment.remove(ONLY_DIGITS, PUNCTUATION).squish == ''
        return true
      end
    elsif ends_with_? comment
      if original_eqls_uppercase? comment
        if comment.remove(NOT_DIGITS)
          unless comment.empty?
            return true
          end
        end
      end
    end
  end

  def acquiescence?(comment)
    if ends_with_? comment
      if original_eqls_uppercase? comment
        if comment.remove(ONLY_DIGITS).eql?('?')
          return true
        end
      elsif !original_eqls_uppercase? comment
        return true
      end
    end
  end

  def sass?(comment)
    comment.chr == ' ' || comment.empty? || comment.chr == "\t"
  end
end
