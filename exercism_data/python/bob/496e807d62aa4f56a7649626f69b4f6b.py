import re
def hey( input_str ):
   ends_q_re    = re.compile("\?$")
   ends_q       = re.search(ends_q_re, input_str)
   no_stmt_re   = re.compile("^\s+$")
   nothing_said = re.search(no_stmt_re, input_str)
   loud_q_re    = re.compile("\s[A-Z]+\s")
   loud_q       = re.search(loud_q_re, input_str)
   word_list    = input_str.split()
   upper_count  = 0
   no_up_count  = 0
   response     = 'Whatever.'
   # import pdb; pdb.set_trace()
   for word in word_list:
     if word.isupper():
       upper_count += 1
     else:
       no_up_count += 1


   if ends_q:
    response = "Sure."
   if nothing_said or len( input_str ) == 0:
     response = "Fine. Be that way!"
   if input_str.isupper() or upper_count > no_up_count:
    response = "Whoa, chill out!"

   return response
