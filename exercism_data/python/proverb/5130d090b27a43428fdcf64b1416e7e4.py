def proverb(input_list, qualifier=None):

    prov = '\n'.join('For want of a {0} the {1} was lost.'.
                     format(input_list[i], input_list[i+1])
                     for i in xrange(len(input_list)-1))

    last = '{0} {1}'.format(qualifier, input_list[0]) \
           if qualifier else input_list[0]

    return '{0}\nAnd all for the want of a {1}.'.format(prov, last)
